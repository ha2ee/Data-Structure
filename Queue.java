import java.util.NoSuchElementException;
/* 1차 */
class Queue2<T> { //큐 클래스 선언(객체를 만들 때 데이터타입을 명시하게 만듬)
    
    class Node<T> { //같은 타입을 받는 노드를 선언
        private T data; //데이터를 선언하고
        private Node<T> next; //다음 노드도 선언
        
        public Node(T data) { // 생성자에서 해당 타입의 데이터를 받아서
            this.data = data; //내부 변수에 저장한다.
        }
    }

    //큐는 앞, 뒤로 주소를 알고 있어야 해서 first,last두개의 멤버변수 선언
    private Node<T> first;
    private Node<T> last;
/* 1차 끝 */    

/* 2차 */
    //함수선언
    //추가하는 함수
    public void add(T item) { //추가할 T타입의 아이템을 하나 받아서
        Node<T> t = new Node(item); // 그아이템을 가지고 노드를 하나 생성한다.

        if(last != null){ //마지막 노드가 있다면?
            last.next = t; //그 뒤에 새로 생성한 노드를 붙이고
        }
        last = t; // t가 이제 마지막 노드가 됨
        if(first == null) { //데이터가 없다면?(첫번째 노드가 null)
            first = last; //같은 값을 할당해준다.
        }
    }

    public T remove() { //큐의 앞에서 요소를 제거하고 반환
        if(first == null){ //큐가 비어있으면
            throw new NoSuchElementException(); //Exception 에러를 던진다.
        }
        //맨 앞의 데이터를 반환하기 전에 그 다음 주소에 있는 애를 first로 만들어줘야 한다.

        T data = first.data; //데이터를 백업하고
        first = first.next; //다음애를 first로 만들어준다.

        if(first == null){ //다음데이터가 없어서 first가 null이 됐다면
            last = null; //last도 같이 변경해주어야 한다.
        }
        return data;
    }

    public T peek() { // 큐의 앞에 있는 요소를 반환 T타입
        if(first == null){ //마찬가지로 널체크를 하고
            throw new NoSuchElementException(); //익셉션 에러 던지기
        }
        //널이 아니면
        return first.data; //현재 first가 가리키고 있는 데이터를 반환한다.
    }

    public boolean isEmpty() { //큐가 비어있는지 확인
        return first == null; //first가 비어있는지 확인하면된다.
    }
/* 2차 끝 */    
}

public class Queue {
    public static void main(String[] args) {
        /* 3차 테스트 */
        Queue2<Integer> q = new Queue2<>(); // 객체 생성
        q.add(1); //데이터 담기
        q.add(2);
        q.add(3);
        q.add(4);

        System.out.println(q.remove()); //1꺼내오면서 출력(제거하면서 반환된값)
        System.out.println(q.remove()); //2꺼내오면서 출력(제거하면서 반환된값)
        System.out.println(q.peek());  //3(제거X)
        System.out.println(q.remove()); //3꺼내오면서 출력(제거하면서 반환된값)
        System.out.println(q.isEmpty()); //비어있는지? false
        System.out.println(q.remove());  //4꺼내오면서 출력(제거하면서 반환된값)
        System.out.println(q.isEmpty()); //비어있는지? true
        /* 3차 끝 */
    }
}
