import java.util.EmptyStackException;
/* 1차 */
class Stack2<T> { //스택 클래스 선언
    
    class Node<T>{//객체를 만들 때 데이터 타입을 명시하도록 하고, 같은 타입을 받는 노드를 하나 선언
        private T data; //데이터를 선언
        private Node<T> next; // 다음 노드(주소) 선언

        public Node(T data) { //생성자에서 해당 타입의 데이터를 하나 받아서
            this.data = data; //내부 변수에 저장
        }
    }

    private Node<T> top; //스택은 맨위에 올라가있는 주소만 알고 있으면 됨
/* 1차끝 */

/* 2차 */
    //함수 구현
    public T pop() {// 가장 위에 있는 노드를 가져오면서 제거하는 함수(스택에서 가장 위에 있는 항목을 제거하고 반환한다.)
        if(top == null){ //탑이 null이면 
            throw new EmptyStackException(); //Exception을 던진다
        }
        //아니면 맨 위에 있는 데이터를 반환하기 전에 그 다음 주소에 있는 애를 탑으로 만들어줘야한다.
        T item = top.data; //데이터를 백업하고
        top = top.next; //다음 애를 탑으로 만들어준다.
        return item; //그리고 데이터를 반환
    }

    //추가하는 함수
    public void push(T item){ //push할 T타입의 item을 하나 받아서
        Node<T> t = new Node<T>(item); //그 item을 가지고 노드를 하나 생성
        t.next = top; //탑 앞에 기존 노드를 위치 시키고
        top = t; // 이제 받은 노드가 top이 된다.
    }

    public T peek() { //T타입의 데이터를 반환(스택의 맨 위에 있는 항목을 반환)
        if(top == null){ //pop과 마찬가지로 null체크
            throw new EmptyStackException(); //널이면 exception을 던짐
        }
        return top.data; //null이 아니면 top이 가지고 있는 데이터를 반환한다.
    }

    public boolean isEmpty() { //스택이 비어있는지 여부를 확인하는 함수
        return top == null; // 탑이 널인지 확인
    }
/* 2차끝 */
}


public class Stack {
    public static void main(String[] args) {
        /* 3차 테스트 */
        Stack2<Integer> s = new Stack2<Integer>(); //스택생성

        s.push(1);//스택 쌓기
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println(s.pop()); //4를 가져오면서 4삭제 후 3이 top으로
        System.out.println(s.pop()); //3을 가져오면서 3삭제 후 2가 top으로
        System.out.println(s.peek()); //2만 가져옴
        System.out.println(s.pop()); //2를 가져오면서 2삭제 후 1이 top으로
        System.out.println(s.isEmpty()); //비어있는지? false
        System.out.println(s.pop()); //1을 가져오면서 1삭제 후 데이터가 없음(null)
        System.out.println(s.isEmpty()); //비어있는지? true
        /* 3차끝 */
    }
}
