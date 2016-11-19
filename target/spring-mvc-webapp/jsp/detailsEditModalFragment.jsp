<%-- 
    Document   : detailsEditModalFragment
    Created on : Oct 31, 2016, 10:17:33 PM
    Author     : Jimmy Cook 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modals</title>
    </head>
    <body>
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Fish Details</h4>
                    </div>
                    <div class="modal-body">
                        <div id="fish-picture"></div>
                        <hr>
                        <table class="table table-bordered">
                            <tr>
                                <th>Name:</th>
                                <td id="fish-name"></td>
                            </tr>
                            <tr>
                                <th>Scientific Name:</th>
                                <td id="fish-scientific-name"></td>
                            </tr>
                            <tr>
                                <th>Picture URL: </th>
                                <td id="fish-picture-url"></td>
                            </tr>
                            <tr>
                                <th>Water Type:</th>
                                <td id="fish-water-type"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit Fish</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="fish-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-name" class="col-md-4 control-label">
                                    Fish Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-name"
                                           placeholder="Fish Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-scientific-name" class="col-md-4 control-label">
                                    Scientific Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-scientific-name"
                                           placeholder="Scientific Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-picture-url" class="col-md-4 control-label">
                                    Picture URL:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-picture-url"
                                           placeholder="Picture URL">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-water-type" class="col-md-4 control-label">
                                    Water Type:
                                </label>
                                <div class="col-md-8">
                                    <label class="radio-inline">
                                        <input type="radio" name="waterEditRadioOptions" id="edit-fresh-water-radio" value="freshwater"> Freshwater
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="waterEditRadioOptions" id="edit-salt-water-radio" value="saltwater"> Saltwater
                                    </label>
                                </div>
                            </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button type="submit" id="edit-button" class="btn btn-default"
                                                data-dismiss="modal">
                                            Edit Fish
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                data-dismiss="modal">
                                            Cancel
                                        </button>
                                        <input type="hidden" id="edit-id">
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
