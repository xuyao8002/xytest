package com.xuyao.test.serialization.xml;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class Jaxb {

    @XmlRootElement(name="ROOT")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Root{
        private Common common;
        private Out out;

        public Common getCommon() {
            return common;
        }

        public void setCommon(Common common) {
            this.common = common;
        }

        public Out getOut() {
            return out;
        }

        public void setOut(Out out) {
            this.out = out;
        }

        @Override
        public String toString() {
            return "Root{" +
                    "common=" + common +
                    ", out=" + out +
                    '}';
        }
    }

    static class Common{

    private String dateTime;

    @XmlElement(name="DateTime")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String DateTime) {
        this.dateTime = DateTime;
    }

    @Override
    public String toString() {
        return "Common{" +
                "dateTime='" + dateTime + '\'' +
                '}';
    }
}
    static class Out{
        private Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Out{" +
                "detail=" + detail +
                '}';
    }
}
    static class Detail{
        private String resultStatus;

    @XmlElement(name="ResultStatus")
    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String ResultStatus) {
        this.resultStatus = ResultStatus;
    }

    @Override
    public String toString() {
        return "Rd{" +
                "resultStatus='" + resultStatus + '\'' +
                '}';
    }
}

    @Test
    public void marshaller() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = jc.createMarshaller();
        Root root = new Root();
        Common common = new Common();
        common.setDateTime("2019-08-19");
        root.setCommon(common);

        Out out = new Out();
        Detail detail = new Detail();
        detail.setResultStatus("1");
        out.setDetail(detail);
        root.setOut(out);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(root, outputStream);
        System.out.println(outputStream.toString());

    }

    @Test
    public void unmarshaller() throws Exception {

        String xmlStr = "<?xml version=\"1.0\" encoding = \"UTF-8\"?>\n" +
        "<ROOT>\n" +
        "  <common>\n" +
        "    <DateTime>2019-08-19</DateTime>\n" +
        "  </common>\n" +
        "  <out>\n" +
        "    <detail>\n" +
        "      <ResultStatus>1</ResultStatus>\n" +
        "    </detail>\n" +
        "  </out>\n" +
        "</ROOT>";
        JAXBContext jc = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Root je = (Root) unmarshaller.unmarshal(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
        System.out.println(je);

    }

}
