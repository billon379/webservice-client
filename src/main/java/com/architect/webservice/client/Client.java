package com.architect.webservice.client;

import com.architect.webservice.service.cxf.CxfJaxUserInterface;
import com.architect.webservice.service.jax.JaxUserInterface;
import com.architect.webservice.service.jax.JaxUserService;
import com.architect.webservice.service.jax.User;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * webservice client
 * 生成服务端接口：
 * 1）wsimport -s . http://127.0.0.1:10000/jax/user?wsdl
 * 2）wsimport -s . http://127.0.0.1:10000/cxf/jax/user?wsdl
 */
public class Client {
    public static void main(String[] args) throws Exception {
        clientJaxWs(); //JAX-WS方式调用
        clientCxfJaxWs(); //CXF-JAX-WS方式调用
        clientCxfJaxRs(); //CXF-JAX-RS方式调用
    }

    /**
     * JAX-WS方式调用
     */
    public static void clientJaxWs() {
        JaxUserService jaxUserService = new JaxUserService();
        JaxUserInterface jaxUserInterface = jaxUserService.getJaxUserInterfacePort();
        User user = jaxUserInterface.queryUser("小白");
        System.out.println("clientJaxWs :" + user.getName() + "," + user.getAge());
    }

    /**
     * CXF-JAX-WS方式调用
     */
    public static void clientCxfJaxWs() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:10000/cxf/jax/user");
        jaxWsProxyFactoryBean.setServiceClass(CxfJaxUserInterface.class); //此处必须使用接口
        CxfJaxUserInterface cxfJaxUserInterface = (CxfJaxUserInterface) jaxWsProxyFactoryBean.create();
        com.architect.webservice.service.cxf.User user = cxfJaxUserInterface.queryUser("小白");
        System.out.println("clientCxfJaxWs :" + user.getName() + "," + user.getAge());
    }

    /**
     * CXF-JAX-RS方式调用
     */
    public static void clientCxfJaxRs() throws Exception {
        String name = "张三";
        URL url = new URL("http://127.0.0.1:10000/cxf/rest/user/" + name);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() == 200) {
            System.out.println(inputStream2String(httpURLConnection.getInputStream()));
        }
    }

    /**
     * 输入流转字串
     *
     * @param in InputStream
     * @return 字串
     */
    public static String inputStream2String(InputStream in) {
        if (in == null) {
            return null;
        }
        String result = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            result = baos.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
