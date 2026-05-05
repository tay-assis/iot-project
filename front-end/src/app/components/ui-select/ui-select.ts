import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ui-select',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ui-select.html',
  styleUrls: ['./ui-select.scss']
})
export class UiSelectComponent {
  @Input() label: string = '';
  @Input() options: any[] = [];
  @Input() displayKey: string = 'name';
  @Input() valueKey: string = 'id';

  @Output() valueChange = new EventEmitter<any>();

  onChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.valueChange.emit(select.value);
  }
}