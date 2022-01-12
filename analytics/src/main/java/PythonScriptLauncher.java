import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class PythonScriptLauncher implements LaunchPythonScript{

    @Override
    public void launcher() {


        ProcessBuilder pb = new ProcessBuilder("python", System.getProperty("user.dir") + "/analytics/predictor/RegresionLineal.py" );

        pb.inheritIO();
        Process process = null;

        try {
            process = pb.start();
            process.waitFor();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert process != null;
        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        try {
            if ((line = bfr.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



