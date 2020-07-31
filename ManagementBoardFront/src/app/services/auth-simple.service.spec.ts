import { TestBed } from '@angular/core/testing';

import { AuthSimpleService } from './auth-simple.service';

describe('AuthSimpleService', () => {
  let service: AuthSimpleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthSimpleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
