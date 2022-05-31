import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {QrCodeService} from "../service/qr-code.service";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products: Product[] = []

  scanType = "SCAN";

  link = '';

  constructor(private productService: ProductService, private qrCodeService: QrCodeService) {

    this.productService.getAllProducts().subscribe(data => {
      this.products = data.content;
    }, err => {
      console.log(err);
    })

  }

  ngOnInit(): void {
  }

  scanCode() {
    let inputs: any = document.querySelectorAll("input[type='file']");
    if (this.scanType === 'SCAN') {
      let formData = new FormData();
      formData.append('file', inputs[0].files[0]);
      this.qrCodeService.decode(formData).subscribe(data => {
        console.log(data);
      }, err => {
        console.log(err)
      })
    }

    if (this.scanType === 'CHECK') {
      let formData = new FormData();
      for (let i = 0; i < inputs.length; i++) {
        formData.append('file' + (i + 1), inputs[i].files[0]);
      }
      this.qrCodeService.check2QRCodes(formData).subscribe(data => {
        console.log(data);
      }, err => {
        console.log(err);
      })
    }

    if (this.scanType === 'LINK') {
      this.qrCodeService.encodeLink(this.link).subscribe(next => {
        console.log(next);
      }, err => {
        console.log(err);
      })
    }

  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(
      () => {
        window.location.reload();
        alert('success!')
      }
    );
  }

  displayImage(input: any) {
    let img1 = document.getElementById("image1");
    img1.setAttribute('src', '');

    let files: File = input.target.files;
    let reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onload = (e) => {
      let string = reader.result as string;
      img1.setAttribute('src', string);
    }
  }

  displayImage2(input: any) {
    let img2 = document.getElementById("image2");
    let file = input.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = e => {
      let stringUrl = reader.result as string;
      img2.setAttribute('src', stringUrl);
    }
  }

  displayUrl() {
    let img1 = document.getElementById("image1");
    img1.setAttribute('src', this.link);
  }
}
