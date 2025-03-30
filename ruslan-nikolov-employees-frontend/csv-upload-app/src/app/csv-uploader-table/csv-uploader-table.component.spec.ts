import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsvUploaderTableComponent } from './csv-uploader-table.component';

describe('CsvUploaderTableComponent', () => {
  let component: CsvUploaderTableComponent;
  let fixture: ComponentFixture<CsvUploaderTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CsvUploaderTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CsvUploaderTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
