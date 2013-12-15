package com.gibsson.basic.service.lib;

/**
 * System-private API for talking to the BasicService.
 *
 * {@hide}
 */
interface IBasicService {
  int getValue();
  int setValue(int val);
}
