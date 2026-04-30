import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Cadastro1 } from './cadastro-1';

describe('Cadastro1', () => {
  let component: Cadastro1;
  let fixture: ComponentFixture<Cadastro1>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Cadastro1]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Cadastro1);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
