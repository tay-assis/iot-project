import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeshboard } from './admin-deshboard';

describe('AdminDeshboard', () => {
  let component: AdminDeshboard;
  let fixture: ComponentFixture<AdminDeshboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDeshboard],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminDeshboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
