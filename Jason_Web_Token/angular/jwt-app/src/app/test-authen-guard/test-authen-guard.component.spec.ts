import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAuthenGuardComponent } from './test-authen-guard.component';

describe('TestAuthenGuardComponent', () => {
  let component: TestAuthenGuardComponent;
  let fixture: ComponentFixture<TestAuthenGuardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestAuthenGuardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestAuthenGuardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
