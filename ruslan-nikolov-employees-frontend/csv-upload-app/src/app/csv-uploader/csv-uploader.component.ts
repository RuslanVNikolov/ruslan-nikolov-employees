import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-csv-uploader',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  template: `
    <div style="text-align:center">
      <h1>CSV File Uploader</h1>
      <input type="file" (change)="onFileSelected($event)" accept=".csv">
      <button (click)="onUpload()" [disabled]="!selectedFile">Upload</button>
    </div>
  `
})
export class CsvUploaderComponent {
  selectedFile: File | null = null;

  constructor(private http: HttpClient) {}

  // Called when the user selects a file
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      console.log('Selected file:', this.selectedFile);
    }
  }

  // Called when the user clicks the upload button
  onUpload(): void {
    if (!this.selectedFile) {
      alert('Please select a file first!');
      return;
    }

    // Create FormData to send the file
    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);

    // POST to the backend's upload endpoint.
    // Ensure your backend is running and CORS is configured appropriately.
    this.http.post('http://localhost:8080/upload', formData)
      .subscribe({
        next: (response) => console.log('Upload successful:', response),
        error: (error) => console.error('Upload error:', error)
      });
  }
}
