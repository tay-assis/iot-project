import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UiTextInput } from './ui-text-input';

describe('UiTextInput', () => {
  let component: UiTextInput;
  let fixture: ComponentFixture<UiTextInput>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UiTextInput],
    }).compileComponents();

    fixture = TestBed.createComponent(UiTextInput);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
