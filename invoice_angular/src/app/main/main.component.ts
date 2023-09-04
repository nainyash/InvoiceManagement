import { Component, OnInit } from '@angular/core';
import { Customer} from '../customer';
import { InvoiceService } from '../invoice.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormComponent } from '../form/form.component';
import { ItemsComponent } from '../items/items.component';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent {
  constructor( private modalservice:NgbModal) {}


  //to open customer add form
openModal(){
  const modalref=this.modalservice.open(FormComponent);
}

//to open items add form
openModal1(){
  const modalref=this.modalservice.open(ItemsComponent);
}
  }

