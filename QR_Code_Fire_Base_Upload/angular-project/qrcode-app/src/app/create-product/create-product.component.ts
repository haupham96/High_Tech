import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../service/category.service";
import {ProductService} from "../service/product.service";
import {Category} from "../model/category";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../model/product";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";
import {Router} from "@angular/router";
import {QrCode} from "../model/qr-code";
import {QrCodeService} from "../service/qr-code.service";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  path = "/PD-";
  product: Product = {};
  categories: Category[] = [];
  qrCode: QrCode = {};

  productForm = new FormGroup({
    name: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    category: new FormControl()
  });

  constructor(private qrCodeService: QrCodeService,
              private categoryService: CategoryService,
              private productService: ProductService,
              private storage: AngularFireStorage,
              private router: Router) {
    this.categoryService.getAllCategory().subscribe(
      data => {
        this.categories = data;
        this.productForm.get('category').setValue(this.categories[0]);
        this.productForm.get('category').setValidators(Validators.required);
        this.productForm.get('category').updateValueAndValidity();
      }
    )
  }

  ngOnInit(): void {
  }

  createProduct() {
    if (this.productForm.valid) {
      this.product = Object.assign({}, this.productForm.value);
      this.productService.createProduct(this.product).subscribe(data => {
        let img = data;
        this.productService.getLatestProduct().subscribe(data => {
          this.product = data;
          this.path += this.product.id + ".png";
          this.storage.upload(this.path, img).snapshotChanges().subscribe(()=>{
            this.router.navigate(['/'])
          })
        })
      }, err => {
        console.log(err)
      })
    }
  }
}
