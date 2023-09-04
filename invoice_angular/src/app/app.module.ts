import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MainComponent } from './main/main.component';
import { ItemsComponent } from './items/items.component';
import { HttpClientModule } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormComponent } from './form/form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InvoiveComponent } from './invoive/invoive.component';
import { MatInputModule } from '@angular/material/input';
import { InvoiceEditComponent } from './invoice-edit/invoice-edit.component';
import { DashComponent } from './dash/dash.component';
import { CustomerlistComponent } from './customerlist/customerlist.component';
import { ItemlistComponent } from './itemlist/itemlist.component';



@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    ItemsComponent,
    FormComponent,
    InvoiveComponent,
    InvoiceEditComponent,
    DashComponent,
    CustomerlistComponent,
    ItemlistComponent,
    ],
  imports: [
    MatIconModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatSelectModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatTableModule,
    MatInputModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
