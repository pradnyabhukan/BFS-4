// TC : O(m*n)
// SC : O(m*n)
class Solution {
    int[][] directions;
    private int countMine(char[][] board, int[] click) {
        int mineCount = 0;
        for(int[] dir : directions) {
            int nr = dir[0] + click[0];
            int nc = dir[1] + click[1];
            if(nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] != 'B') {
                if(board[nr][nc] == 'M') {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }
    public char[][] updateBoard(char[][] board, int[] click) {
        directions = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};
        Queue<int[]> q = new LinkedList<>();
        int mines = countMine(board, click);
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        if(mines == 0) {
            q.add(click);
        } else {
            board[click[0]][click[1]] = (char)(mines + '0');
        }
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0 ; i<size ; i++) {
                int[] currClick = q.poll();
                if(board[currClick[0]][currClick[1]] == 'M' ) {
                    board[currClick[0]][currClick[1]] = 'X';
                    break;
                } else if(board[currClick[0]][currClick[1]] == 'E') {
                    int mineCount = countMine(board, currClick);
                    if(mineCount == 0) {
                        for(int[] dir : directions) {
                            int nr = dir[0] + currClick[0];
                            int nc = dir[1] + currClick[1];
                            if(nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] != 'B') {
                                if( board[nr][nc] != 'M' && !Character.isDigit(board[nr][nc])) {
                                    q.add(new int[]{nr, nc});
                                } else if(board[nr][nc] == 'M') {
                                    mineCount++;
                                }
                            }
                        }
                        board[currClick[0]][currClick[1]] = 'B';
                    } else {
                        board[currClick[0]][currClick[1]] = (char)(mineCount + '0');
                    }
                }
            }
        }
        return board;
    }
}
