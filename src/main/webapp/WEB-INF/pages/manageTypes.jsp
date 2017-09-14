<%@page session="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Managing Resource Types</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />
<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="ResourcesView.html">View</a></li>
        <li><a href="#">Add</a></li>
        <li class="active"><a href="#">Edit</a></li>
        <li><a href="ResourcesRequest.html">Requests <span class="badge">3</span></a></li>
    </ul>
    <br>
    <h3>Manage resource types and its properties</h3>
    <br>
    <div id="nestable-menu">
        <button type="button" class="btn btn-primary" data-action="expand-all">Expand All</button>
        <button type="button" class="btn btn-primary" data-action="collapse-all">Collapse All</button>
    </div>

    <div class="dd scrollable" id="nestable"></div>

    <p><strong>Serialised Output (per list)</strong></p>

    <textarea id="nestable-output"></textarea>

    <div>
        <br>
        <h4>Properties of the selected resource type</h4>
        <hr>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Characteristic Name</th>
                <th>Details</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Char 1</td>
                <td>Det 1</td>
                <td>
                    <button type="button" class="btn btn-link" data-toggle="modal"
                            data-target="#editProp"><span class="glyphicon glyphicon-edit"></span>
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-remove"></span>
                    </button>
                </td>
            </tr>
            <tr>
                <td>Char2</td>
                <td>Det 2</td>
                <td>
                    <button type="button" class="btn btn-link" data-toggle="modal"
                            data-target="#editProp"><span class="glyphicon glyphicon-edit"></span>
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-remove"></span>
                    </button>
                </td>
            </tr>
            <tr>
                <td>Char3</td>
                <td>Det 3</td>
                <td>
                    <button type="button" class="btn btn-link" data-toggle="modal"
                            data-target="#editProp"><span class="glyphicon glyphicon-edit"></span>
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-remove"></span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>

        <div id="editProp" class="modal fade" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            ×
                        </button>
                        <h4>Edit Property</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <form class="form">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="title">Property title</label>
                                            <input type="text" class="form-control" id="title" placeholder="Title"
                                                   value="Capacity of engine">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="name">Property name</label>
                                            <input type="text" class="form-control" id="name" placeholder="Name"
                                                   value="Capacity">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="form-group">
                                            <label for="units">Measuring units</label>
                                            <input type="text" class="form-control" id="units"
                                                   placeholder="e.g. cc (cubical centimeters)" value="cc">
                                        </div>
                                    </div>

                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label for="valueType">Value type</label>
                                            <select class="form-control" id="valueType" placeholder="Select value type">
                                                <option value="0">Select value type</option>
                                                <option value="1">integer</option>
                                                <option value="2" selected>decimal</option>
                                                <option value="3">string</option>
                                                <option value="4">boolean</option>
                                                <option value="5">timestamp</option>
                                                <option value="6">resource property</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label for="default">Default value</label>
                                            <input type="text" class="form-control" id="default" placeholder="0"
                                                   value="100">
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="regex">Validation regex</label>
                                    <input type="text" class="form-control" id="regex"
                                           placeholder="regex string, e.g. [0-9]+\.[0-9]*[1-9] for decimal with non-zero last digit"
                                           value="[0-9]+\.[0-9]*[1-9]">
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" id="essential"><span
                                            class="font-bold">Essential</span></label>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" id="multi"><span class="font-bold">Multivalued</span></label>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" id="req"><span
                                            class="font-bold">Required</span></label>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" id="secured"><span
                                            class="font-bold">Secured</span></label>
                                </div>
                                <div class="container-fluid pull-right">
                                    <button type="reset" class="btn btn-primary" data-dismiss="modal">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="pull-right">
        <button class="btn">Cancel</button>
        <button class="btn btn-primary">Save</button>
    </div>
</div>
<br>
<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; Lv257_Java</p>
    </div>
</footer>
<script>
    $(document).ready(function () {
        var updateOutput = function(e) {
            var list = e.length ? e : $(e.target),
                output = list.data('output');
            if(window.JSON) {
                output.val(window.JSON.stringify(list.nestable('serialize')));
            }
            else {
                output.val('JSON browser support required for this demo.');
            }
            console.log(window.JSON.stringify(list.nestable('serialize')));
        };

        var json = [${inputJson}];

        // activate Nestable for list 1
        $('#nestable').nestable({
            json: json,
            contentCallback: function(item) {
                var content = item.catname || '' ? item.catname : item.id;
                content += ' <i>(id = ' + item.id + ')</i>';

                return content;
            }
        }).on('change', updateOutput);

        // output initial serialised data
        updateOutput($('#nestable').data('output', $('#nestable-output')));

        $('#nestable-menu').on('click', function (e) {
            var target = $(e.target),
                action = target.data('action');
            if (action === 'expand-all') {
                $('.dd').nestable('expandAll');
            }
            if (action === 'collapse-all') {
                $('.dd').nestable('collapseAll');
            }
        });
    });
</script>
</body>
</html>
