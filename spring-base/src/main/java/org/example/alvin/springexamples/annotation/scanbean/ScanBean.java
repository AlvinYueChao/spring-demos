package org.example.alvin.springexamples.annotation.scanbean;

import org.springframework.stereotype.Component;

@Component
@BeanScanner(value = {
    "org.example.alvin.springexamples.annotation.scanbean.mybasepackage"
})
public class ScanBean {

}
