import { TestBed } from '@angular/core/testing';
import { StudentModel } from '../models/student.model';
import { StudentService } from './student';


describe('Student', () => {
  let service: StudentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
