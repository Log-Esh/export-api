package com.nielsen.discover.demo.controller;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nielsen.discover.demo.service.ExportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api/export", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/stories", tags = "Discover Export Reports",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class ExportController {
  
  public static final String PATH_JOB_IDS = "/{appId}/jobIds";
  public static final String PATH_JOB_RESPONSE = "/jobResponse";
  public static final String APP_ID_DEFAULT_VALUE = "GENERIC";
  
  @Autowired
  private ExportService exportService;
  
  /**
   *
   * @param exportTitle
   * @param extension
   * @param reportingServiceResponse
   * @param response
   * @throws IOException
   */
  @ApiOperation(value = "Get Export by Reporting Service Responses",
      notes = "This operation will return excel/ppt with file extension based on multiple reporting service responses")
  @GetMapping(value = PATH_JOB_RESPONSE,
      produces = {MediaType.APPLICATION_JSON_VALUE, "application/pdf",
          "application/vnd.openxmlformats-officedocument.presentationml.presentation",
          "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void getExportByRSResponses(final HttpServletResponse response)
      throws IOException {

    // update the multiply reportTemplateDto
    // if (isMultiply) {
    // updateMultiplyReportTemplateDto(reportingServiceResponse, null);
    // }
    this.exportService.getExport(response);
  }

}
