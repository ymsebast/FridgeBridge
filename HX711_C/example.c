#include "hx711.h"

int main(){
  int err = 0;
  int priority = 10;
  err = setPriority(priority);
  if (err)
  {
    printf("\n!!! Failed to set process priority to %d\n", priority);
    return 1;
  }

  HX711 hx;

  unsigned char clock_pin = (unsigned) 20;
  unsigned char data_pin = (unsigned) 21;
  unsigned int samples = 1;
  
  err = initHX711(&hx, clock_pin, data_pin);
  if (err)
  {
    printf("\n!!! Failed to init HX711 struct !!!\n");
  }

  setupGPIO(&hx);

  reset(&hx);

  // START doing work

  int raw_data = 0;
  FILE *data_file;
  
  raw_data = getRawDataMean(&hx, samples);
  //printf("Raw data: %d \n", raw_data);
  data_file = fopen("weight.txt", "w");
  fprintf(data_file, "%d", raw_data);
  fclose(data_file);
  //sleep(5);

  // STOP doing work

  cleanGPIO(&hx);

  return 0;
}
