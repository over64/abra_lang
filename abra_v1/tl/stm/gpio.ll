;void gpio_set_mode(uint32_t gpioport, uint8_t mode, uint8_t cnf,
;		   uint16_t gpios);
declare void @gpio_set_mode(i32, i8, i8, i16)

;void gpio_toggle(uint32_t gpioport, uint16_t gpios);
declare void @gpio_toggle(i32, i16)
