package com.company;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.CrackModule;

public class Main {

    public static BigInteger getValueFromResponce(CloseableHttpResponse response)
    {
        try
        {
            // receive content from server
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));

            // read and store content
            String line = "";
            StringBuffer strResponse = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                strResponse.append(line);
            }

            // find and store received N = P*Q from content
            String reg = "DECIMAL FORM:</b><br><center><input style=width:633px; value=";
            Pattern pattern = Pattern.compile("DECIMAL FORM:</b><br><center><input style=width:633px; value=[0-9]*>");
            Matcher matcher = pattern.matcher(strResponse);
            while (matcher.find())
            {
                return new BigInteger(strResponse.subSequence(matcher.start() + reg.length(), matcher.end()-1).toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return BigInteger.ZERO;
    }

    public static void main(String[] args) {

        // module n = p*q
        BigInteger n = BigInteger.ZERO;

        // connect to server and send ID as query to generate n = p*q
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://pti.kpi.ua/cgi-bin/mmzi_d4_l2/gen.rb?id=1234");
        try
        {
            CloseableHttpResponse response = httpclient.execute(httpget);

             n = getValueFromResponce(response);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        // generate fake square root, send it to server and
        // check if it can take p,q where n = p*q
        boolean isFind = false;
        CrackModule crmod = new CrackModule();
        while(isFind == false)
        {
            crmod.generateT(n);
            BigInteger t2 = crmod.getSqrT(n);

            try
            {
                URI uri = new URIBuilder().setScheme("http").setHost("pti.kpi.ua").setPath("/cgi-bin/mmzi_d4_l2/root.rb").
                        setParameter("x4",t2.toString(10)).build();
                HttpGet fakehttpget = new HttpGet(uri);
//                System.out.print("query = " + fakehttpget.toString() + "\n");

                CloseableHttpResponse serverResponse = httpclient.execute(fakehttpget);

                BigInteger z = getValueFromResponce(serverResponse);

                if(!z.equals(BigInteger.ZERO))
                    isFind = crmod.isFind_P_Q(z, n);
                else
                {
                    isFind = true;
                    System.out.print("Error connection to server ... \n");
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }

        System.out.print("------------ MODULO WAS BREAKDOWN INTO P AND Q, P*Q = N -----------------");

    }
}
