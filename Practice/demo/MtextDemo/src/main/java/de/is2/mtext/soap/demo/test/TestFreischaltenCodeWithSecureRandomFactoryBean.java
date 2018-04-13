package de.is2.mtext.soap.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Random;

public class TestFreischaltenCodeWithSecureRandomFactoryBean {
    
    @Autowired
    private SecureRandomFactoryBean secureRandomFactoryBean;
    private final int length = 8;
    
    @PostConstruct
    public void init() {
        Assert.isTrue(secureRandomFactoryBean != null);
    }
    
    public String generate() throws Exception {
        final String charsUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String charsLower = "abcdefghijklmnopqrstuvwxyz";
        final String nums = "0123456789";
        final String specialSymbols = "!@#$%^&*()_+-=.,/;':?><~*/-+[]{}\\~|´`";
        final String[] charset = {charsUpper, charsLower, nums, specialSymbols};
        return generate(charset, length);
    }
    
    public String generate(String[] charset, int length) throws Exception {
        Random rnd = new Random();
        final byte[] buf = new byte[length];
        secureRandomFactoryBean = new SecureRandomFactoryBean();
        secureRandomFactoryBean.getObject().nextBytes(buf);
        final StringBuffer freischaltcode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int key = rnd.nextInt(4);
//            if (key == 4 && freischaltcode.length() <= 4) {
//                for (int j = key; j > 0; j--) {
//                    char code = charset[j].charAt(Math.abs(buf[i]) % charset[j].length());
//                    if (freischaltcode.toString().indexOf(code) < 0) {
//                        freischaltcode.append(code);
//                    }
//                }
//            }
            char code = charset[key].charAt(Math.abs(buf[i]) % charset[key].length());
            if (freischaltcode.toString().indexOf(code) < 0) {
                freischaltcode.append(code);
            }
        }
        return freischaltcode.toString();
    }
    
    public static void main(String[] args) throws Exception {
        TestFreischaltenCodeWithSecureRandomFactoryBean secureRandomFactoryBean = new TestFreischaltenCodeWithSecureRandomFactoryBean();
//        secureRandomFactoryBean.init();
        System.out.println("Generated code: " + secureRandomFactoryBean.generate());
    }
}
