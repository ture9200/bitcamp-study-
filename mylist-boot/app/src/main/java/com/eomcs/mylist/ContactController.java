package com.eomcs.mylist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //annotation
// 이 클래스가 클라이언트 요청처리 담당자임을 표시한다.
// 이 표시 (애노테이션)가 붙어있어야만 스프링부트가 이 클래스를 인식한다.
public class ContactController {

  String[] contacts=new String[5];
  int size = 0;

  @RequestMapping("/contact/list")
  //Request Mapping 요청이 들어오면 리스트랑 연결을 하겠다. 
  //메서드와 요청을 mapping 
  //리턴한 값을 클라이언트에게 그대로 json형식의 문자열로 바꿔서 
  public Object list() {
    String[] arr= new String[size];  //배열의 저장된 값만 복사할 새 배열을 만든다. 
    for (int i = 0; i < size; i++) {
      arr[i] = contacts[i]; //전체 배열에서 값이 들어있는 항목만 복사한다. 
    }
    return arr; // 복사한 항목들을 담고 있는 새 배열을 리턴한다. 
  }

  @RequestMapping("/contact/add")
  public Object add(String name, String email, String tel, String company) {
    String contact = name + "," + email + "," + tel + "," + company;
    contacts[size++] = contact;
    return size;
  }

  @RequestMapping ("/contact/get")
  public Object get(String email) { 
    for (int i = 0; i < size; i++) {
      //학습용 
      //      String contact = contacts[i]; //예) "u1, u1@test.com, 1111,비트캠프"
      //      String[] values= contact.split(","); //예) ["u1", "u1@test.com", "1111","비트캠프"]
      //      if (values[1].equals(email)) {  
      //        //예)u1@test.com
      if(contacts[i].split(",")[1].equals(email)) {
        return contacts[i]; 
        // if (contacts[i].split(",")[1])
      }
    }
    return "";
  }

  @RequestMapping("/contact/update")
  public Object update(String name, String email, String tel, String company) { 
    String contact = name + "," + email + "," + tel + "," + company;
    for(int i = 0; i < size; i++) {
      if(contacts[i].split(",")[1].equals(email)) {
        contacts[i] = contact;
        System.out.println("myname:"+myname+"paramName"+paramName));
        return 1;
      }
    }
    return 0;
  }

  @RequestMapping("/contact/delete")
  public Object delete(String email) { 
    for (int i = 0; i < size; i++) {
      if(contacts[i].split(",")[1].equals(email)) {
        //현재 위치의 다음 항목에서 배열 끝까지 반복하며 앞으로 값을 당긴다. 
        for(int j=i+1; j < size; j++) {
          contacts[j-1] = contacts[j];
        }
        size--;
        return 1; 
      }
    }
    return 0;
  }
}