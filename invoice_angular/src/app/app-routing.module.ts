import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InvoiveComponent } from './invoive/invoive.component';
import { DatePipe } from '@angular/common';
import { InvoiceEditComponent } from './invoice-edit/invoice-edit.component';
import { DashComponent } from './dash/dash.component';
import { CustomerlistComponent } from './customerlist/customerlist.component';
import { ItemlistComponent } from './itemlist/itemlist.component';


const routes: Routes = [
  //to open create invoice

  {path:'invoices',component:InvoiveComponent},
  //to open edit or update

  { path: 'update/:id', component: InvoiceEditComponent },

  //for the list of all data
  {path:'dash',component:DashComponent},

  //for the list of customers
  {path:'customers',component:CustomerlistComponent},

  //for the list of items
  {path:'items',component:ItemlistComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers : [DatePipe],
})
export class AppRoutingModule { }
