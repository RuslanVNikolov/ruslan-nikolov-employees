import { Component } from '@angular/core';
import { CsvUploaderTableComponent } from './csv-uploader-table/csv-uploader-table.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CsvUploaderTableComponent],
  template: `<app-csv-uploader-table></app-csv-uploader-table>`
})
export class AppComponent {}
