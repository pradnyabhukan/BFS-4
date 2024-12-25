// TC : O(n^2)
// SC : O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flatBoard = new int[n*n];
        int r = n-1;
        int c = 0;
        int idx = 0;
        Boolean isRight = true;
        while(idx < flatBoard.length) {
            if(board[r][c] == -1) {
                flatBoard[idx] = -1;
            } else {
                flatBoard[idx] = board[r][c] - 1;
            }
            idx++;
            if(isRight) {
                c++;
                if(c == n) {
                    isRight = false;
                    r--; c--;
                }
            } else {
                c--;
                if(c < 0) {
                    isRight = true;
                    r--; c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0 ; i<size ; i++) {
                int currPos = q.poll();
                if(currPos >= (n*n - 1)) {
                    return level;
                }
                for(int j=1 ; j<=6 ; j++) {
                    int newIdx = currPos + j;
                    if(newIdx >= n*n) {
                        break;
                    }
                    if(flatBoard[newIdx] != -2) {
                        if(flatBoard[newIdx] == -1) {
                            q.add(newIdx);
                        } else {
                            q.add(flatBoard[newIdx]);
                        }
                    }
                    flatBoard[newIdx] = -2;
                }
            }
            level++;
        }
        return -1;
    }
}
