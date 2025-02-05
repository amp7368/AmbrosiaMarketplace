package com.ambrosia.markets.database.model.base.image;

import com.ambrosia.markets.database.model.base.BareBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.File;
import java.util.UUID;
import net.dv8tion.jda.api.utils.FileUpload;

@Entity
@Table(name = "image")
public class DImage extends BareBaseEntity {

    @Id
    private UUID id;
    @Lob
    private File image;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String extension;

    public DImage(File image, String name, String extension) {
        this.image = image;
        this.name = name;
        this.extension = extension;
    }

    public FileUpload getDiscordImage() {
        return FileUpload.fromData(image, name + "." + extension);
    }
}
