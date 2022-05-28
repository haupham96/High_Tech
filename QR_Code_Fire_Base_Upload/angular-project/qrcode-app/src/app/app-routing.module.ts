import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ListProductComponent} from "./list-product/list-product.component";
import {CreateProductComponent} from "./create-product/create-product.component";
import {EditProductComponent} from "./edit-product/edit-product.component";


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'list-product'},
  {path: 'list-product', component: ListProductComponent},
  {path: 'create-product', component: CreateProductComponent},
  {path: 'edit-product/:id', component: EditProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
