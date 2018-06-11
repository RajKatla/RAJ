package com.identitye2e.fs.pojo;

public class FileMetadata {

    private String name;
    private String mimeType;
    private long size;
    private String extension;

    private FileMetadata(final FileMetadataBuilder builder) {
        this.name = builder.name;
        this.mimeType = builder.mimeType;
        this.size = builder.size;
        this.extension = builder.extension;
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    public static class FileMetadataBuilder {
        private String name;
        private String mimeType;
        private long size;
        private String extension;

        public FileMetadataBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public FileMetadataBuilder withMIMEType(final String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public FileMetadataBuilder withSize(final long size) {
            this.size = size;
            return this;
        }

        public FileMetadataBuilder withExtension(final String extension) {
            this.extension = extension;
            return this;
        }

        public FileMetadata build() {
            return new FileMetadata(this);
        }
    }
}
