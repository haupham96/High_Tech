import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService) {
    
    this.productService.getAllProducts().subscribe(data => {
      this.products = data.content;
    }, error => {
      console.log(error);
    })

  }

  ngOnInit(): void {
  }

}
