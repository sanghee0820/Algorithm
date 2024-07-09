import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void solution(int n, String[] ipAddress) throws IOException {
        String[] binaryIpAddress = new String[n];
        for(int i = 0; i < n; i++){
            String[] ip = ipAddress[i].split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
            for(int j = 0; j < 4; j++){
                stringBuilder.append(
                        String.format("%8s", Integer.toBinaryString(Integer.parseInt(ip[j]))).replace(' ', '0')
                );
            }
            binaryIpAddress[i] = stringBuilder.toString();
        }

        int diffIndex = 0;
        for(int i = 0; i < 32; i++){
            boolean flag = false;
            for(int j = 0; j < n - 1; j++){
                if(binaryIpAddress[j].charAt(i) != binaryIpAddress[j + 1].charAt(i)){
                    diffIndex = i;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
            if(i == 31){
                diffIndex = 32;
            }
        }

        String network = binaryIpAddress[0].substring(0, diffIndex).concat("0".repeat(32 - diffIndex));
        String networkMask = "1".repeat(diffIndex).concat("0".repeat(32 - diffIndex));
        if(diffIndex == 32){
            networkMask = "1".repeat(32);
            network = binaryIpAddress[0];
        }
        String[] resultNetwork = new String[4];
        String[] resultNetworkMask = new String[4];
        for(int i = 0; i < 4; i++){
            resultNetwork[i] = String.valueOf(Long.parseLong(network.substring(i * 8, (i + 1) * 8), 2));
            resultNetworkMask[i] = String.valueOf(Long.parseLong(networkMask.substring(i * 8, (i + 1) * 8), 2));
        }

        bw.write(String.join(".", resultNetwork) + "\n");
        bw.write(String.join(".", resultNetworkMask) + "\n");
    }



    public static void main(String[] args) throws IOException {
        var input = br.readLine();
        var n = Integer.parseInt(input);

        String[] ipAddress = new String[n];

        for(int i = 0; i < n; i++){
            ipAddress[i] = br.readLine();
        }

        solution(n, ipAddress);

        bw.close();
        br.close();

    }
}
