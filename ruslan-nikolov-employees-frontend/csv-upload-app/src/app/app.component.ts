import { Component } from '@angular/core';
import { CsvUploaderComponent } from './csv-uploader/csv-uploader.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CsvUploaderComponent],
  template: `<app-csv-uploader></app-csv-uploader>`
})
export class AppComponent { }
