package com.ms.spring_file_upload;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUploadTests {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/files";

    @Test
    public void givenExistentPath_whenConfirmFileExists_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.exists(p));
    }

    @Test
    public void givenNonexistentPath_whenConfirmsFileNotExists_thenCorrect() {
        Path p = Paths.get(FILE_PATH + "/in_existent_file.txt");

        assertTrue(Files.notExists(p));
    }

    @Test
    public void givenDirPath_whenConfirmNotRegularFile_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertFalse(Files.isRegularFile(p));
    }

    @Test
    public void givenExistentDirPath_whenConfirmsReadable_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isReadable(p));
    }

    @Test
    public void givenExistentDirPath_whenConfirmsWritable_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isWritable(p));
    }

    @Test
    public void givenExistentDirPath_whenConfirmsExecutable_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isExecutable(p));
    }

    @Test
    public void givenSameFilePaths_whenConfirmsIsSame_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isSameFile(p, p));
    }

    @Test
    public void givenFilePath_whenCreatesNewFile_thenCorrect() throws IOException {
        String filename = "my_file_" + UUID.randomUUID() + ".txt";
        Path p = Paths.get(FILE_PATH + "/" + filename);
        assertFalse(Files.exists(p));

        Files.createFile(p);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenDirPath_whenCreatesNewDir_thenCorrect() throws IOException {
        String dirName = "myDir_" + UUID.randomUUID();
        Path p = Paths.get(FILE_PATH + "/" + dirName);
        assertFalse(Files.exists(p));

        Files.createDirectory(p);
        assertTrue(Files.exists(p));
        assertFalse(Files.isRegularFile(p));
        assertTrue(Files.isDirectory(p));
    }

    @Test
    public void givenDirPath_whenFailsToCreateRecursively_thenCorrect() throws IOException {
        String dirName = "myDir_" + UUID.randomUUID() + "/subdir";
        Path p = Paths.get(FILE_PATH + "/" + dirName);
        assertFalse(Files.exists(p));

        Files.createDirectory(p);
    }

    @Test
    public void givenDirPath_whenCreatesRecursively_thenCorrect() throws IOException {
        Path dir = Paths.get(FILE_PATH + "/myDir_" + UUID.randomUUID());
        Path subDir = dir.resolve("subdir");
        assertFalse(Files.exists(dir));
        assertFalse(Files.exists(subDir));

        Files.createDirectories(subDir);
        assertTrue(Files.exists(dir));
        assertTrue(Files.exists(subDir));
    }

    @Test
    public void givenFilePath_whenCreatesTempFile_thenCorrect() throws IOException {
        String prefix = "log_";
        String suffix = ".txt";
        Path p = Paths.get(FILE_PATH);

        Files.createTempFile(p, prefix, suffix);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenPath_whenCreatesTempFileWithDefaults_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH);

        Files.createTempFile(p, null, null);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenNoFilePath_whenCreatesTempFileInTempDir_thenCorrect() throws IOException {
        Path p = Files.createTempFile(null, null);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenPath_whenCreatesTempDirectory_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH);
        String tempDir = UUID.randomUUID().toString();

        Files.createTempDirectory(p, tempDir);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenPath_whenCreatesTempDirectoryWithDefaults_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH);

        Files.createTempDirectory(p, null);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenNoPath_whenCreatesTempDirectoryInTempDir_thenCorrect() throws IOException {
        Path p = Files.createTempDirectory(null);
        assertTrue(Files.exists(p));
    }

    @Test
    public void givenPath_whenDeletes_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH + "/fileToDelete.txt");
        assertFalse(Files.exists(p));

        Files.createFile(p);
        assertTrue(Files.exists(p));

        Files.delete(p);
        assertFalse(Files.exists(p));
    }

    @Test
    public void givenInExistentFile_whenDeleteFails_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH + "/in-existentFile.txt");
        assertFalse(Files.exists(p));

        Files.delete(p);
    }

    @Test
    public void givenInExistentFile_whenDeleteIfExistsWorks_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH + "/in-existentFile.txt");
        assertFalse(Files.exists(p));

        Files.deleteIfExists(p);
    }

    @Test
    public void givenPath_whenFailsToDeleteNonEmptyDir_thenCorrect() throws IOException {
        Path dir = Paths.get(FILE_PATH + "/emptyDir" + UUID.randomUUID());

        Files.createDirectory(dir);
        assertTrue(Files.exists(dir));

        Path file = dir.resolve("file.txt");
        Files.createFile(file);

        Files.delete(dir);
        assertTrue(Files.exists(dir));
    }

    @Test
    public void givenFilePath_whenCopiesToNewLocation_thenCorrect() throws IOException {
        Path dir1 = Paths.get(FILE_PATH + "/firstDir_" + UUID.randomUUID());
        Path dir2 = Paths.get(FILE_PATH + "/otherDir_" + UUID.randomUUID());

        Files.createDirectory(dir1);
        Files.createDirectory(dir2);

        Path file1 = dir1.resolve("fileToCopy.txt");
        Path file2 = dir2.resolve("fileToCopy.txt");

        Files.createFile(file1);

        assertTrue(Files.exists(file1));
        assertFalse(Files.exists(file2));

        Files.copy(file1, file2);
        assertTrue(Files.exists(file2));
    }

    @Test
    public void givenPath_whenCopyFailsDueToExistingFile_thenCorrect() throws IOException {
        Path dir1 = Paths.get(FILE_PATH + "/firstDir_" + UUID.randomUUID());
        Path dir2 = Paths.get(FILE_PATH + "/otherDir_" + UUID.randomUUID());

        Files.createDirectory(dir1);
        Files.createDirectory(dir2);

        Path file1 = dir1.resolve("fileToCopy.txt");
        Path file2 = dir2.resolve("fileToCopy.txt");

        Files.createFile(file1);
        Files.createFile(file2);

        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));

//        Files.copy(file1, file2);
        Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void givenFilePath_whenMovesToNewLocation_thenCorrect() throws IOException {
        Path dir1 = Paths.get(FILE_PATH + "/firstDir_" + UUID.randomUUID());
        Path dir2 = Paths.get(FILE_PATH + "/otherDir_" + UUID.randomUUID());

        Files.createDirectory(dir1);
        Files.createDirectory(dir2);

        Path file1 = dir1.resolve("fileToCopy.txt");
        Path file2 = dir2.resolve("fileToCopy.txt");

        Files.createFile(file1);
        assertTrue(Files.exists(file1));
        assertFalse(Files.exists(file2));

        Files.move(file1, file2);
        assertTrue(Files.exists(file2));
        assertFalse(Files.exists(file1));
    }

    @Test
    public void givenFilePath_whenMoveFailsDueToExistingFile_thenCorrect() throws IOException {
        Path dir1 = Paths.get(FILE_PATH + "/firstDir_" + UUID.randomUUID());
        Path dir2 = Paths.get(FILE_PATH + "/otherDir_" + UUID.randomUUID());

        Files.createDirectory(dir1);
        Files.createDirectory(dir2);

        Path file1 = dir1.resolve("fileToCopy.txt");
        Path file2 = dir2.resolve("fileToCopy.txt");

        Files.createFile(file1);
        Files.createFile(file2);
        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));

//        Files.move(file1, file2);
        Files.move(file1, file2, StandardCopyOption.REPLACE_EXISTING);

        assertTrue(Files.exists(file2));
        assertFalse(Files.exists(file1));
    }
}
