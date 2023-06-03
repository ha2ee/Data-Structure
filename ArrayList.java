/* 1차 */
class ArrayList2{ //ArrayList 클래스 선언
    private Object[] data; //배열에 들어갈 데이터는 오브젝트로 하기
    private int size; // 배열의 크기를 저장할 사이즈
    private int index; // 다음 데이터를 추가할 위치를 기억하고 있는 index 선언
    
    public ArrayList2() { //ArrayList를 생성하면
        this.size = 1; //사이즈를 1로 할당하고
        this.data = new Object[this.size]; // 사이즈만큼 배열방을 생성하고
        this.index = 0; // 새로 들어올 데이터는 0번방에 들어가게 될 테니까 인덱스는 0으로 초기화해주자
    }

    public void add(Object obj){ // 배열방에 데이터 추가하기

        /* 3-1차 */
        //데이터가 추가 될 때 인덱스와 사이즈를 보고
        System.out.println("index: "+this.index + ", size: "+ this.size + ", data size: " + this.data.length);  
        /* 3-1차끝 */

        if(this.index == this.size -1){ //먼저 배열방에 데이터를 넣기전에 방이 다 찼는지 먼저 비교(확인)
            doubling(); // 다 찼으면 더블링을 실행하고 추가로 계속 진행
        }
        data[this.index] = obj; //공간이 있으면 가져온 데이터를 배열방 맨 끝에 추가
        this.index++; //데이터가 하나 추가되었으니 인덱스를 1늘려주자
    }

    private void doubling() {//위에서 사용한 doubling 함수 정의
        this.size = this.size * 2; //현재 사이즈를 2배로 늘리기
        Object[] newData = new Object[this.size]; //새로운 배열방을 2배 큰 사이즈로 생성하고
        for(int i = 0; i < data.length; i++){ //현재 배열방을 돌면서
            newData[i] = data[i]; //새로만든 배열방에 전부 복사한다.(Object니까 포인터를 복사하는것)
        }
        this.data = newData; //그리고 나서 배열방의 주소가 현재 데이터라고 할당해준다.

        /* 3-3차 */
        //더블링을 하고 나서 인덱스랑 사이즈가 어떻게 변했는지 확인
        System.out.println("*** index: "+ this.index + ", size: " + this.size + ", data size: " + this.data.length);
        /* 3-3차끝 */
    }

    public Object get(int i) throws Exception { //인덱스 번호를 가지고 데이터를 가져오는 함수 정의
        if(i > this.index-1){ //인덱스 번호가 0보다 작거나 갖고 있는 데이터 이상인 경우 Exception을 throw한다.
            throw new Exception("ArrayIndexOutOfBound");
        } else if(i < 0){
            throw new Exception("Negative Value");
        }
        return this.data[i]; //아닌 경우에는 해당 배열방의 데이터를 반환한다.
    }

    public void remove(int i) throws Exception { //삭제하는 함수 정의
        if(i > this.index-1){ //get과 마찬가지로 인덱스가 데이터 범위 내에 있는지 확인해서 Exception을 throw
            throw new Exception("ArrayIndexOutOfBound");
        } else if(i < 0){
            throw new Exception("Negative Value");
        }

        /* 3-2차 */
        //데이터가 삭제 될 때 어떤 데이터가 삭제되는지 보기
        System.out.println("data removed: " + this.data[i]);
        /* 3-2차끝 */
        
        //i를 2를 받았다면 x는 2가 되고, 2번인덱스에 있는값 = 3번인덱스에 있는값으로 대체를 함
        for(int x = i; x < this.data.length -1; x++){ // 삭제할 데이터를 기준으로 한칸씩 앞으로 쉬프트해서 빈자리를 메꿔주고
            data[x] = data[x + 1]; 
        }
        this.index--; //인덱스를 1 줄여주면 현재 인덱스에 있는 데이터는 있어도 안쓰겠다는 의미니까 그냥 무시가 된다.
    }
    /* 1차끝 */
}

public class ArrayList {
    public static void main(String[] args) throws Exception{
        /* 2차(테스트) */
        ArrayList2 al = new ArrayList2(); //ArrayList 생성
        al.add("0"); //문자열이 Object니까 0~9까지 넣어준다.
        al.add("1");
        al.add("2");
        al.add("3");
        al.add("4");
        al.add("5");
        al.add("6");
        al.add("7");
        al.add("8");
        al.add("9");
        System.out.println(al.get(5));
        al.remove(5);
        System.out.println(al.get(5));
        /* 2차끝 */
    }
}
