import java.io.*;

public enum BestScore {
        VALUE;
        private int best_score = 0;

        public int bestScore (){
            if (best_score == 0) {
                    read_score();
            }
                return best_score;



        }

        private void read_score()  {
            BufferedReader br = null ;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("BestScore.conf"))));
                best_score= Integer.parseInt(br.readLine());
            } catch (FileNotFoundException e) {
                best_score =0 ;
            } catch (IOException e) {
                best_score = 0;
            }
            finally {
                if (br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        public void writeScore(int score){
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("BestScore.conf"))));
                bw.write(score +"");
                best_score= score;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                if (bw !=null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }



