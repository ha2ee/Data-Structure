/* 1차 시작 */
import java.util.LinkedList;
class HashTable2 { //해시테이블 클래스 선언.

    class Node { //해시테이블에 저장할 데이터를 노드에 담기

        String key; // 노드를 검색 할 키
        String value; // 검색결과로 보여줄 값

        public Node(String key, String value) { // 노드를 생성 할 때 키와 값을 받아서 객체를 생성
            this.key = key;
            this.value = value;
        }

        String value() { //value를 가져오는 get
            return value;
        }
        void value(String value){ //value를 저장하는 set
            this.value = value;
        }
    }//Node class 끝.

    LinkedList<Node>[] data; //데이터를 저장할 LinkedList를 배열로 선언 LinkedList에는 방금 선언한 Node가 들어갈것
    
    HashTable2(int size) { // 해시테이블을 선언하는 순간, 해시테이블을 어느크기로 만들어놓을지 크기를 미리 정해서 배열 방을 미리 만들어 놓음
        this.data = new LinkedList[size];
    }
/* 여기까지 1차. */


/* 여기부터 2차 */
    /* 본격적인 액션을 만들기에 앞서 기본적으로 필요한 3개의 함수 */
    int getHashCode(String key){ //가장 중요한 함수, 해시 알고리즘을 가지고 있는 해시함수 -> 키를 받아서 해시코드를 반환
        int hashcode = 0; //해시코드를 0으로 초기화
        for(char c : key.toCharArray()){ //입력받은 키(문자열)을 돌면서 각 레터에 아스키 값을 가져와서 해시코드에 더해준다.
            hashcode += c;
        }
        return hashcode;
    }

    int convertToIndex(int hashcode){ //해시코드를 받아서 배열방에 인덱스로 변환해주는 함수
        return hashcode % data.length;
    }

    //인덱스로 배열방을 찾은 이후에, 배열방에 노드가 여러개 존재하는 경우 검색키를 가지고 해당 노드를 찾아오는 함수
    Node searchKey(LinkedList<Node> list, String key){ 
        if(list == null) return null; //배열방이 null이면 null을 반환
        for(Node node : list){ //그렇지 않으면 배열방에 있는 LinkedList를 돌면서 
            if(node.key.equals(key)){ //노드의 키가 검색하는 키와 같은지를 확인해서
                return node; // 같으면 노드 반환
            }
        }
        return null; //같은 데이터를 못찾았으면 null을 반환
    }
/* 여기까지 2차 */    


/* 여기서부터 3차 */    
    /* 액션 */
    void put(String key, String value) { //데이터를 받아서 저장하는 함수, 함수의 인자로는 저장할 key와 value를 받는다.
        int hashcode = getHashCode(key); //키를 가지고 해시코드를 받아온다.
        int index = convertToIndex(hashcode); // 받아온 해시코드로 저장할 배열방 번호를 받아온다.

        /* 5차 */
        //데이터를 넣을때 해시코드는 뭔지, 배열방은 어디로 할당 받는지 확인
        System.out.println(key + ", hashcode(" + hashcode + "), index(" + index + ")");
        /* 여기까지 5차 */

        LinkedList<Node> list = data[index]; //배열방 번호를 이용해서 기존 배열방에 있는 데이터를 가져오고
        if(list == null){ // 배열방이 null이면
            list = new LinkedList<Node>(); //LinkedList를 생성한다.
            data[index] = list; //그리고 해당 리스트를 배열방에 넣어준다.
        }
        Node node = searchKey(list, key); //그리고 배열방에 혹시 기존에 해당 키로 데이터를 가지고 있는지 노드를 받아온다.
        if(node == null){ //노드가 null이면 데이터가 없다는 뜻이니까
            list.addLast(new Node(key, value)); //받아온 정보를 가지고 노드 객체를 생성해서 리스트에 추가한다.
        } else {
            node.value(value); //노드가 null이 아닌 경우에는 해당 노드의 값을 대체해주는걸로 중복키를 처리한다.
        }
    }

    String get(String key){ //키를 가지고 데이터를 가져오는 함수
        int hashcode = getHashCode(key); //키를 가지고 해시코드를 받아옴
        int index = convertToIndex(hashcode); // 받아온 해시코드를 가지고 인덱스를 받아옴
        LinkedList<Node> list = data[index]; //인덱스에서 LinkedList를 가져옴
        Node node = searchKey(list, key); //LinkedList안에 해당 키를 가지는 노드를 검색해 옴
        return node == null? "Not found" : node.value(); //노드를 못찾았으면 "Not found" 찾았으면 찾은 밸류를 반환
    }
/* 여기까지 3차 */    
}


public class HashTable {
    public static void main(String[] args) {
        /* 4차. 테스트 */
        HashTable2 h = new HashTable2(3); //해시테이블은 3개로 고정된 배열방을 생성
        
        //검색할 키와 값을 줌
        h.put("sung", "She is pretty"); 
        h.put("jin", "She is a model");
        h.put("hee", "She is an angel");
        h.put("min", "She is cute");
        
        //각 데이터를 해시테이블에서 잘 가져오는지 확인
        System.out.println(h.get("sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("hee"));
        System.out.println(h.get("min"));
        
        //jae는 없으니까 Not found
        System.out.println(h.get("jae"));

        //데이터 덮어쓰기
        h.put("sung", "She is beautiful");
        System.out.println(h.get("sung"));
        /* 여기까지 테스트 */
        
    }
}