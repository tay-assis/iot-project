import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UiBox } from './ui-box';

describe('UiBox', () => {
  let component: UiBox;
  let fixture: ComponentFixture<UiBox>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UiBox],
    }).compileComponents();

    fixture = TestBed.createComponent(UiBox);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
