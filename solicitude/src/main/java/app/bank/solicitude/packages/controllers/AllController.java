package app.bank.solicitude.packages.controllers;

import app.bank.solicitude.packages.entities.*;
import app.bank.solicitude.packages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/solicitude")
public class AllController {
    @Autowired
    private SolicitudeService solicitudeService;

    @Autowired
    private TypeLoanService typeLoanService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ClientDocumentService clientDocumentService;

    @Autowired
    private LoanRequirementService loanRequirementService;
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/")
    public ResponseEntity<List<SolicitudeEntity>> findAll() {
        List<SolicitudeEntity> solicitudes = solicitudeService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<List<SolicitudeEntity>> solicitudeByClient(@PathVariable("id") Long id) {
        List<SolicitudeEntity> solicitudes = solicitudeService.getAllSolicitudesByClientId(id);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudeEntity> solicitudeById(@PathVariable("id") Long id) {
        SolicitudeEntity solicitude = solicitudeService.getById(id);
        return ResponseEntity.ok(solicitude);
    }

    @GetMapping("/change/solicitude/client/by/solicitude/id/client/choose/aprove/loan/by/id/solicitude/{id}")
    public ResponseEntity<SolicitudeEntity> changeState(@PathVariable Long id){
        SolicitudeEntity solicitudeChanged = solicitudeService.changeState(id);
        return ResponseEntity.ok(solicitudeChanged);
    }

    @GetMapping("/gettype")
    public ResponseEntity<List<TypeLoanEntity>> getAllTypeLoans() {
        List<TypeLoanEntity> types = typeLoanService.getAllTypeLoans();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/get/type/{type}")
    public ResponseEntity<TypeLoanEntity> getTypeLoan(@PathVariable String type){
        TypeLoanEntity loan = typeLoanService.getTypeLoan(type);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/get/documents")
    public ResponseEntity<List<DocumentEntity>> getAllDocuments() {
        List<DocumentEntity> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/finddocument/{id}")
    public ResponseEntity<DocumentEntity> findDocumentById(@PathVariable Long id){
        DocumentEntity document = documentService.findDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/find/document/byMinimun/{minimum}")
    public ResponseEntity<ArrayList<DocumentEntity>> findDocumentByMinimunRequirements(@PathVariable Boolean minimum){
        ArrayList<DocumentEntity> document = documentService.findDocumentByMinimunRequirements(minimum);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/find/document/by/title/{title}")
    public ResponseEntity<DocumentEntity> findDocumentByTittle(@PathVariable String title){
        DocumentEntity document = documentService.findDocumentByTitle(title);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/find/document/by/byMinimun/minimum/{minimum}")
    public ResponseEntity<List<Long>> findDocumentByMinimunRequirements1(@PathVariable Boolean minimum){
        List<Long> document = documentService.findDocumentByMinimunRequirements1(minimum);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/find/list/document/by/client/id/{id}")
    public ResponseEntity<List<ClientDocumentEntity>> findByClientId(@PathVariable Long id) {
        List<ClientDocumentEntity> clientDocuments = clientDocumentService.findByClientId(id);
        return ResponseEntity.ok(clientDocuments);
    }

    @GetMapping("/get/document/ids/by/client/id/listdocument/{clientId}")
    public ResponseEntity<List<Long>> getDocumentsIdsByClientId(@PathVariable Long clientId) {
        List<Long> ids = clientDocumentService.getDocumentIds(clientId);
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/{clientId}/{title}")
    public ResponseEntity<ClientDocumentEntity> findByClientIdAndTitle(@PathVariable Long clientId, @PathVariable String title){
        ClientDocumentEntity clientDocument = clientDocumentService.findByClientIdAndTitle(clientId, title);
        return ResponseEntity.ok(clientDocument);
    }

    @GetMapping("/find/all/requirement")
    public ResponseEntity<List<LoanRequirementEntity>> findAll1() {
        List<LoanRequirementEntity> requirements = loanRequirementService.getAllLoanRequirements();
        return ResponseEntity.ok(requirements);
    }

    @GetMapping("/find/loan/requirement/by/type/loan/id/typeid/{typeId}")
    public ResponseEntity<LoanRequirementEntity> findByTypeLoanId(@PathVariable Long typeId){
        LoanRequirementEntity loanRequirement = loanRequirementService.findByTypeLoanId(typeId);
        return ResponseEntity.ok(loanRequirement);
    }

    @GetMapping("/find/max/months/by/type/loan/id/integer/maxMonths/{id}")
    public ResponseEntity<Integer> findMaxMonthsByTypeLoanId(@PathVariable Long id){
        Integer maxMonths = loanRequirementService.findMaxMonthsByTypeLoanId(id);
        return ResponseEntity.ok(maxMonths);
    }

    @GetMapping("/interest/{id}/rate")
    public ResponseEntity<BigDecimal> findInterestRateByTypeLoanId(@PathVariable Long id) {
        BigDecimal interestRate = loanRequirementService.findInterestRateByTypeLoanId(id);
        return ResponseEntity.ok(interestRate);
    }

    @GetMapping("/find/max/amount/by/type/loan/id/bigdecimal/max/amount/{id}")
    public ResponseEntity<BigDecimal> findMaxAmountByTypeLoanId(@PathVariable Long id){
        BigDecimal maxAmount = loanRequirementService.findMaxAmountByTypeLoanId(id);
        return ResponseEntity.ok(maxAmount);
    }

    @GetMapping("/find/loan/requirement/by/type/loan/id/list/documents/list/loan/{id}")
    public ResponseEntity<List<DocumentEntity>> findLoanRequirementsByTypeLoanId(@PathVariable Long id){
        List<DocumentEntity> listDocuments = loanRequirementService.findDocumentsByTypeLoanId(id);
        return ResponseEntity.ok(listDocuments);
    }

    @GetMapping("/find/loan/requirement/by/type/loan/id/documents/list/loan/by/id/{id}")
    public ResponseEntity<List<Long>> findLoanRequirementsByTypeLoanId1(@PathVariable Long id){
        List<Long> listDocuments = loanRequirementService.findDocumentsByTypeLoanId1(id);
        return ResponseEntity.ok(listDocuments);
    }

    @GetMapping("/percent/rate/{rate}/rate")
    public ResponseEntity<Double> percentRate(@PathVariable double rate){
        double percentRate = loanRequirementService.percentRate(rate);
        return ResponseEntity.ok(percentRate);
    }

    @GetMapping("/{month}/years/loan")
    public ResponseEntity<Double> years(@PathVariable double month){
        double yearsLoan = loanRequirementService.year(month);
        return ResponseEntity.ok(yearsLoan);
    }











    @PostMapping("/")
    public ResponseEntity<SolicitudeEntity> saveSolicitude(@RequestBody SolicitudeEntity solicitude) {
        SolicitudeEntity saved = solicitudeService.saveSolicitude(solicitude);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/createtype")
    public ResponseEntity<TypeLoanEntity> createTypeLoan(@RequestBody TypeLoanEntity typeLoan){
        TypeLoanEntity loan = typeLoanService.createTypeLoan(typeLoan);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/create/document")
    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document){
        DocumentEntity documento = documentService.save(document);
        return ResponseEntity.ok(documento);
    }

    @PostMapping("/save/client/document")
    public ResponseEntity<ClientDocumentEntity> saveClientDocument(@RequestBody ClientDocumentEntity document){
        ClientDocumentEntity clientDocument = clientDocumentService.saveClientDocument(document);
        return ResponseEntity.ok(clientDocument);
    }

    @PostMapping("/file/{client_id}/{document_type}")
    public ResponseEntity<Boolean> saveClientDocumentWithFile(@PathVariable Long client_id, @PathVariable Long document_type, @RequestParam("file") MultipartFile file) throws Exception {
        Boolean clientDocumentUploaded = clientDocumentService.uploadClientDocument(client_id, document_type, file);
        return ResponseEntity.ok(clientDocumentUploaded);
    }

    @PostMapping("/updates/loan/requirement/save")
    public ResponseEntity<LoanRequirementEntity> save(@RequestBody LoanRequirementEntity loanRequirement){
        LoanRequirementEntity updated = loanRequirementService.updateLoanRequirement(loanRequirement);
        return ResponseEntity.ok(updated);
    }









    @PutMapping("/{id}/{state}/{executiveId}")
    public ResponseEntity<SolicitudeEntity> updateSolicitude(@PathVariable("id") Long id, @PathVariable("state") int state, @PathVariable Long executiveId) {
        SolicitudeEntity updated = solicitudeService.updateSolicitude(id, executiveId, state);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/")
    public ResponseEntity<SolicitudeEntity> saveEvaluated(@RequestBody SolicitudeEntity solicitude) {
        SolicitudeEntity evaluated = solicitudeService.saveSolicitudeEvaluated(solicitude);
        return ResponseEntity.ok(evaluated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentEntity> updateDocument(@RequestBody DocumentEntity document){
        DocumentEntity documento = documentService.save(document);
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/updatedocument")
    public ResponseEntity<ClientDocumentEntity> updateClientDocument(@RequestBody ClientDocumentEntity document){
        ClientDocumentEntity clientDocument = clientDocumentService.updateClientDocument(document);
        return ResponseEntity.ok(clientDocument);
    }

    @PutMapping("/update/loanrequirement")
    public ResponseEntity<LoanRequirementEntity> updateLoanRequirement(@RequestBody LoanRequirementEntity loanRequirement){
        LoanRequirementEntity updated = loanRequirementService.updateLoanRequirement(loanRequirement);
        return ResponseEntity.ok(updated);
    }











    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDocument(@PathVariable Long id) throws Exception{
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteClientDocument(@PathVariable Long id) throws Exception{
        clientDocumentService.deleteClientDocument(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/file/downloadById/{documentId}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable Long documentId) throws Exception{
        ClientDocumentEntity clientDocument = clientDocumentService.downloadClientDocumentById(documentId);
        String contentType = fileUploadService.getContentType(clientDocument.getRutaDocumento());
        ByteArrayResource resource = new ByteArrayResource(clientDocument.getDocumento());

        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + clientDocument.getRutaDocumento() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
