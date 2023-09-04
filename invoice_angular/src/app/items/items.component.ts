import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InvoiceService } from '../invoice.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {
  itemForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private itemService: InvoiceService,
    private activeModal: NgbActiveModal
  ) { }

  //initial load
  ngOnInit(): void {
    this.itemForm = this.formBuilder.group({
      name: ['', Validators.required],
      itemprice: ['', [Validators.required, Validators.pattern('[1-9][0-9]*')]],
    });
  }

  //to add items to the database

  onSubmit(): void {
    if (this.itemForm.valid) {
      const itemData = {
        name: this.itemForm.value.name,
        itemprice: this.itemForm.value.itemprice,
      };

      this.itemService.addItem(itemData).subscribe(
        (response) => {
          console.log('Item added successfully:', response);
          this.itemForm.reset();
          this.activeModal.close();
        },
        (error: any) => {
          if (error.status === 409) {
            alert('Item Name already exists. Please choose a different name.');
          } else {
            console.error('Error adding item:', error);
          }
          this.itemForm.reset();
          this.activeModal.close();
        }
      );
    }
    //location.reload();
  }
}
