package algo.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* MST : 프림 알고리즘 이용 */
public class MST2_Prim {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// --
		int N = Integer.parseInt(in.readLine().trim());
		int[][] input = new int[N][N]; // 정점 크기 만큼 인접행렬
		boolean[] visited = new boolean[N]; // 신장 트리 선택 여부 채킹
		int[] minEdge = new int[N]; // 신장 트리에 포함된 정점으로부터 자신과 연결하는 간선 비용 중 최소비용

		// -- 인접행렬 input 초기화
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(tokens.nextToken());
			}
			// 최소값을 위해서 정수형 최대값으로 초기화
			minEdge[i] = Integer.MAX_VALUE;
		}

		// --
		int minVertex, min; // 최소정점, 최소 정점의 간선비용
		int result = 0; // MST 비용
		minEdge[0] = 0; // 임의의 시작점 비용으로 초기화 설정

		// -- 1단계 정점중심 해결: 모든 정점수만큼 반복하면서 모든 정점을 연결
		for (int c = 0; c < N; c++) {
			min = Integer.MAX_VALUE; // 초기값을 최대값으로 설정
			minVertex = 0; // 임의정점: 0

			// -- N 개의 정점 반복하면서 가장 유리한(최소비용) 정점 선택
			for (int i = 0; i < N; ++i) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];	//   현재 간선 비용이 최소이니 갱신
					minVertex = i;	// 현재 정점으로 최소 정점(신장트리) 설정
				}
			}

			visited[minVertex] = true;	// 선택된 정점으로 신장트리 포함시킴
			result += min;	// 선택된 정점의 비용을 mst 누적

			// -- 2단계:
			// 선택된 최소비용 정점과 신장트리 구성에 포함되지 않는 타정점으로의 최소비용 갱신
			for (int i = 0; i < N; i++) {
				if (!visited[i]	// 해당 정점이 신장트리에 포함되어 있지 않고
						&& input[minVertex][i] != 0	// 인접해있는 정점이고
						&& minEdge[i] > input[minVertex][i]) {	
					// i에 해당하는 정점이 다른 정점에서 자신한테 연결하려할 때 간선의 최소비용이 크다면 갱신  
					minEdge[i] = input[minVertex][i];	// 가장 유리한 비용으로 갱신
				}
			}
		}
		// 모든 정점이 연결한 간선 비용 mst 출력
		System.out.println(result);
	}
}
