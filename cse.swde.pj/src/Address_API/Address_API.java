/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Address_API;

/**
 *
 * @author rlarl
 */
import java.net.URLEncoder;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Address_API {
    
    String mode;
    String adr;

    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null) 
            return null;
        return nValue.getNodeValue();
    }
    
    public void get_Data() {
        adr = dial.Adr_Field.getText();
        if(dial.Adr_RButton.isSelected()){
            mode = "dong";
        }else if(dial.Radr_RButton.isSelected()){
            mode = "road";
        }
    }
    
    public DefaultListModel set_Data() {
            try{
                // parsing할 url 지정(API 키 포함해서)
                StringBuilder urlBuilder = new StringBuilder("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=LpLDw%2BFnCnZXrE7I%2BNQ%2B8S%2B4qTivSSNr24c2%2FBklZJ56uam8su1Zp872RQuPR%2BVoKehO2yofWOyrcbfLwkKRXw%3D%3D"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("searchSe","UTF-8") + "=" + URLEncoder.encode(mode, "UTF-8")); /*dong : 동(읍/면)명road :도로명[default]post : 우편번호*/
                urlBuilder.append("&" + URLEncoder.encode("srchwrd","UTF-8") + "=" + URLEncoder.encode(adr, "UTF-8")); /*검색어*/
                urlBuilder.append("&" + URLEncoder.encode("countPerPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 출력될 개수를 지정*/
                urlBuilder.append("&" + URLEncoder.encode("currentPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*출력될 페이지 번호*/
                String url = urlBuilder.toString();

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag 
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("newAddressListAreaCd");
                System.out.println("파싱할 리스트 수 : "+ nList.getLength());
                DefaultListModel model = new DefaultListModel();
                for(int temp = 0; temp < nList.getLength(); temp++){
                        Node nNode = nList.item(temp);
                        if(nNode.getNodeType() == Node.ELEMENT_NODE){

                                Element eElement = (Element) nNode;
                                if(mode.equals("dong")){
                                    model.addElement("("+getTagValue("zipNo", eElement)+")/"+getTagValue("rnAdres", eElement));
                                }else{
                                    model.addElement("("+getTagValue("zipNo", eElement)+")/"+getTagValue("lnmAdres", eElement));
                                }
                        }	// for end
                }	// if end
                return model;
            } catch (Exception e){	
                    e.printStackTrace();
                    return null;
            }	// try~catch end
            
    }	// main end
}	// class end