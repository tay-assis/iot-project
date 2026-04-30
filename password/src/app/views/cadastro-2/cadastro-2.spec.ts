import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Cadastro2 } from './cadastro-2';

describe('Cadastro2', () => {
  let component: Cadastro2;
  let fixture: ComponentFixture<Cadastro2>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Cadastro2]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Cadastro2);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
