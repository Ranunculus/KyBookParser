package com.ranunculus;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created by Ranunculus on 7/05/17.
 */
@Service
public class ParserService {

    final int capacity = 1024;

    public void parseFromFile(String fileName) {
        try {
            File file = ResourceUtils.getFile(fileName);
//            Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            AsynchronousFileChannel fileChannel =
                    AsynchronousFileChannel.open(Paths.get(ClassLoader.getSystemResource(fileName).toURI()), StandardOpenOption.READ);

            System.out.println(Paths.get(ClassLoader.getSystemResource(fileName).toURI()).getFileName());
            ByteBuffer buffer = ByteBuffer.allocate(capacity);
            long position = 0;

            Future<Integer> operation = fileChannel.read(buffer, position);

            while(!operation.isDone());

            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            System.out.println(new String(data));
            buffer.clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
