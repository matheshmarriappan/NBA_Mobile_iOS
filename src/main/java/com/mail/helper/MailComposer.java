package com.mail.helper;

/**
 * MailComposer.
 * @author Automation Team
 */
public class MailComposer {

  /**
   * shost.
   */
  private String shost;

  /**
   * sport.
   */
  private String sport;

  /**
   * sfrom.
   */
  private String sfrom;

  /**
   * sto.
   */
  private String sto;

  /**
   * ssubject.
   */
  private String ssubject;

  /**
   * sbody.
   */
  private String sbody;

  /**
   * sattachementText.
   */
  private String sattachmentText;

  /**
   * sattachmentPath.
   */
  private String sattachmentPath;

  /**
   * sattachmentTextName.
   */
  private String sattachmentTextName;

  /**
   * bolAuth.
   */
  private Boolean bolAuth;

  /**
   * getsHost.
   * @return String String
   */
  public final String getsHost() {
    return shost;
  }

  /**
   * setsHost.
   * @param shost1 shost
   */
  public final void setsHost(final String shost1) {
    this.shost = shost1;
  }

  /**
   * getsPort.
   * @return String String
   */
  public final String getsPort() {
    return sport;
  }

  /**
   * setsPort.
   * @param sport1 sport
   */
  public final void setsPort(final String sport1) {
    this.sport = sport1;
  }

  /**
   * getsFrom.
   * @return String
   */
  public final String getsFrom() {
    return sfrom;
  }

  /**
   * setsFrom.
   * @param sfrom1 sfrom
   */
  public final void setsFrom(final String sfrom1) {
    this.sfrom = sfrom1;
  }

  /**
   * getsTo.
   * @return String
   */
  public final String getsTo() {
    return sto;
  }

  /**
   * setsTo.
   * @param sto1 sto
   */
  public final void setsTo(final String sto1) {
    this.sto = sto1;
  }

  /**
   * getsSubject.
   * @return String
   */
  public final String getsSubject() {
    return ssubject;
  }

  /**
   * setsSubject.
   * @param ssubject1 ssubject
   */
  public final void setsSubject(final String ssubject1) {
    this.ssubject = ssubject1;
  }

  /**
   * getsBody.
   * @return String String
   */
  public final String getsBody() {
    return sbody;
  }

  /**
   * setsBody.
   * @param sbody1 sbody
   */
  public final void setsBody(final String sbody1) {
    this.sbody = sbody1;
  }

  /**
   * getBolAuth.
   * @return Boolean boolean
   */
  public final Boolean getBolAuth() {
    return bolAuth;
  }

  /**
   * setBolAuth.
   * @param bolAuth1 bolAuth
   */
  public final void setBolAuth(final Boolean bolAuth1) {
    this.bolAuth = bolAuth1;
  }

  /**
   * get attachment path.
   * @return String String
   */
  public final String getsAttachmentPath() {
    if (sattachmentPath != null) {
      return sattachmentPath;
    } else {
      return "";
    }
  }

  /**
   * setsAttachmentpath.
   * @param sattachment1 sattachment
   */
  public final void setsAttachmentPath(final String sattachment1) {
    this.sattachmentPath = sattachment1;
  }

  /**
   * .get attachment text.
   * @return String String
   */
  public final String getsAttachmentText() {
    if (sattachmentText != null) {
      return sattachmentText;
    } else {
      return "";
    }
  }

  /**
   * setsAttachmentText.
   * @param sattachment sattachment
   */
  public final void setsAttachmentText(final String sattachment) {
    this.sattachmentText = sattachment;
  }

  /**
   * get attachment text name.
   * @return String String
   */
  public final String getsAttachmentTextName() {
    if (sattachmentTextName != null) {
      return sattachmentTextName;
    } else {
      return "";
    }
  }

  /**
   * setsAttachmentTextName.
   * @param sattachment sattachment
   */
  public final void setsAttachmentTextName(final String sattachment) {
    this.sattachmentTextName = sattachment;
  }
}
