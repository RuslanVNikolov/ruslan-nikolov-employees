import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface Colleagues {
  firstEmployeeId: string;
  secondEmployeeId: string;
  projects: { [projectId: string]: number };
  totalDaysTogether: number;
}

interface TableRow {
  firstEmployee?: string;
  secondEmployee?: string;
  projectId: string;
  daysWorked: number;
  isTotal?: boolean;
}

@Component({
  selector: 'app-csv-uploader-table',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './csv-uploader-table.component.html',
  styleUrls: ['./csv-uploader-table.component.css']
})
export class CsvUploaderTableComponent {
  selectedFile: File | null = null;
  colleague: Colleagues | null = null;
  tableRows: TableRow[] = [];

  constructor(private http: HttpClient) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      console.log('Selected file:', this.selectedFile);
    }
  }

  onUpload(): void {
    if (!this.selectedFile) {
      alert('Please select a file first!');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);

    this.http.post<Colleagues>('http://localhost:8080/upload', formData)
      .subscribe({
        next: data => {
          console.log('Upload successful, received colleague data:', data);
          this.colleague = data;
          this.generateTableRows();
        },
        error: error => {
          console.error('Upload error:', error);
          alert('Error uploading file.');
        }
      });
  }

  generateTableRows(): void {
    if (!this.colleague) { return; }
    this.tableRows = [];
    Object.entries(this.colleague.projects).forEach(([projectId, days]) => {
      this.tableRows.push({
        firstEmployee: this.colleague!.firstEmployeeId,
        secondEmployee: this.colleague!.secondEmployeeId,
        projectId,
        daysWorked: days
      });
    });
    this.tableRows.push({
      projectId: '',
      daysWorked: this.colleague.totalDaysTogether,
      isTotal: true
    });
  }
}
