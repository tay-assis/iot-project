import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ui-box',
   imports: [CommonModule],
  standalone: true,
  templateUrl: './ui-box.html',
  styleUrls: ['./ui-box.scss']
})
export class UiBoxComponent {
  @Input() title: string = '';
}
