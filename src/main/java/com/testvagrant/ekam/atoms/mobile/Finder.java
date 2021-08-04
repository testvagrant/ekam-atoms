package com.testvagrant.ekam.atoms.mobile;

import lombok.*;
import org.openqa.selenium.By;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Finder {
  private By iosFindBy;
  private By androidFindBy;
}
