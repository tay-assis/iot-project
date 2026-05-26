import { TestBed } from '@angular/core/testing';

import { Enroll } from './enroll';

describe('Enroll', () => {
  let service: Enroll;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Enroll);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
