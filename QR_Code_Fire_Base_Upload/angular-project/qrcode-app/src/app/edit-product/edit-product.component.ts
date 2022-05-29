import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {Category} from "../model/category";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../service/category.service";
import {ProductService} from "../service/product.service";
import {AngularFireStorage} from "@angular/fire/storage";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  path = "/PD-";
  product: Product = {};
  categories: Category[] = [];
  productForm = new FormGroup({
    id: new FormControl(),
    name: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    category: new FormControl()
  });


  constructor(private categoryService: CategoryService,
              private productService: ProductService,
              private storage: AngularFireStorage,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.categoryService.getAllCategory().subscribe(
      data => {
        this.categories = data;
        this.activatedRoute.paramMap.subscribe((param: ParamMap) => {
          let id = +param.get('id');
          this.productService.findById(id).subscribe(data => {
            this.product = data;
            this.productForm.get('category').setValue(this.product.category);
            this.productForm.get('name').setValue(this.product.name);
            this.productForm.get('price').setValue(this.product.price);
            this.productForm.get('category').setValidators(Validators.required);
            this.productForm.get('id').setValue(this.product.id);
            this.productForm.get('id').setValidators(Validators.required);
            this.productForm.updateValueAndValidity();
          })
        })
      }
    )
  }

  ngOnInit(): void {
  }

  compare(t1: Category, t2: Category): boolean {
    return t1 && t2 ? t1.id === t2.id : t1 === t2;
  }

  editProduct() {
    if (this.productForm.valid) {
      this.product = this.productForm.value;
      this.productService.editProduct(this.product).subscribe(image => {
        this.path += this.product.id + ".png";
        this.storage.upload(this.path, image).snapshotChanges().subscribe(
          () => {
            this.router.navigate(['/list-product']);
          }
        );
      }, error => {
        console.log(error)
      })
    }
  }
}
