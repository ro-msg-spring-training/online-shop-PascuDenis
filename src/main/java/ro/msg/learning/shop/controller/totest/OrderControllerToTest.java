package ro.msg.learning.shop.controller.totest;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(value = "test")
public class OrderControllerToTest {
}